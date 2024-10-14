import logging
from http import HTTPStatus
from io import BytesIO

from fastapi import APIRouter, UploadFile, HTTPException

from ..service import discipline_service, student_service

router = APIRouter()
log = logging.getLogger("uvicorn.error")


@router.post(
    path="/upload/excel/students",
    status_code=HTTPStatus.ACCEPTED
)
async def get_students(file: UploadFile):
    if (file.content_type not in
            ['application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
             'application/vnd.ms-excel']):
        log.error(f"Wrong file format: {file.content_type}")
        raise HTTPException(status_code=400, detail="Wrong file format")

    content = await file.read()
    student_service.get_students(BytesIO(content))
    # call kafka service


@router.post(
    path="/upload/excel/disciplines",
    status_code=HTTPStatus.ACCEPTED
)
async def get_disciplines(file: UploadFile):
    if (file.content_type not in
            ['application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
             'application/vnd.ms-excel']):
        log.error(f"Wrong file format: {file.content_type}")
        raise HTTPException(status_code=400, detail="Wrong file format")

    content = await file.read()
    discipline_service.get_disciplines(BytesIO(content))
    # call kafka service
