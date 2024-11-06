import logging
from http import HTTPStatus
from io import BytesIO

from fastapi import APIRouter, UploadFile, HTTPException

from ..messaging import producer
from ..schema.models import Student, Discipline
from ..service import discipline_service, student_service

router = APIRouter()
log = logging.getLogger("uvicorn.error")


@router.post(
    path="/upload/excel/students/",
    status_code=HTTPStatus.ACCEPTED
)
async def get_students(file: UploadFile, major: str, course: int):
    if (file.content_type not in
            ['application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
             'application/vnd.ms-excel']):
        log.error(f"Wrong file format: {file.content_type}")
        raise HTTPException(status_code=400, detail="Wrong file format")

    content = await file.read()
    students: list[Student] = student_service.get_students(BytesIO(content), course)

    for student in students:
        await producer.send_student(student, major)


@router.post(
    path="/upload/excel/disciplines",
    status_code=HTTPStatus.ACCEPTED
)
async def get_disciplines(file: UploadFile, major: str):
    if (file.content_type not in
            ['application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
             'application/vnd.ms-excel']):
        log.error(f"Wrong file format: {file.content_type}")
        raise HTTPException(status_code=400, detail="Wrong file format")

    content = await file.read()
    disciplines: list[Discipline] = discipline_service.get_disciplines(BytesIO(content))

    for discipline in disciplines:
        await producer.send_discipline(discipline, major)
