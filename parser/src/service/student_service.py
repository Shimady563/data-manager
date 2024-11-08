import logging
from io import BytesIO

from pandas import DataFrame

from . import excel_parser
from ..schema.models import Student

log = logging.getLogger("uvicorn.error")


def get_students(file: BytesIO, group: str, course: int) -> list[Student]:
    log.info(f"Processing excel file with students information")
    students: list[Student] = []
    sheet: DataFrame = excel_parser.parse_first_sheet(file)

    first_row_index = sheet.iloc[0].index.values[0]
    if first_row_index is not None:
        name = first_row_index
        students.append(
            Student(
                full_name=name,
                group=group,
                course=course
            )
        )

    for i, row in sheet.iterrows():
        for j, col in row.items():
            students.append(
                Student(
                    full_name=col,
                    group=group,
                    course=course
                )
            )

    return students
