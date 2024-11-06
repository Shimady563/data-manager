import logging
from io import BytesIO

from pandas import DataFrame

from . import excel_parser
from ..schema.models import Student

log = logging.getLogger("uvicorn.error")


def get_students(file: BytesIO, course: int) -> list[Student]:
    log.info(f"Processing excel file with students information")
    students: list[Student] = []
    sheet: DataFrame = excel_parser.parse_first_sheet(file)

    for i, row in sheet.iterrows():
        if row.index.values[0] is not None:
            vals = row.index.values[0].split()
            students.append(
                Student(
                    name=vals[1],
                    surname=vals[0],
                    patronymic=vals[2],
                    course=course
                )
            )
        for j, col in row.items():
            vals = col.split()
            students.append(
                Student(
                    name=vals[1],
                    surname=vals[0],
                    patronymic=vals[2],
                    course=course
                )
            )

    return students
