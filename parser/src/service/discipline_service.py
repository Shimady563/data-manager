import logging
from io import BytesIO

from pandas import DataFrame
from pandas import isnull

from . import excel_parser
from ..schema.models import Discipline

sheet_name = "ПланСвод"
log = logging.getLogger("uvicorn.error")


def get_disciplines(file: BytesIO) -> list[Discipline]:
    log.info(f"Processing excel file with disciplines information")
    plan_sheet = excel_parser.parse_sheet(file, sheet_name)
    column_name: str = "-.3"
    exclude = "Дисциплины"
    columns: dict[str, int] = _parse_column_names(plan_sheet)
    disciplines: dict[str, list[int]] = _parse_disciplines(
        plan_sheet,
        columns,
        column_name,
        exclude
    )
    mapped_disciplines: list[Discipline] = []

    for discipline in disciplines.keys():
        mapped_disciplines.append(Discipline(name=discipline, terms=disciplines[discipline]))

    return mapped_disciplines


def _parse_column_names(sheet: DataFrame) -> dict[str, int]:
    log.info(f"Parsing column information from sheet: {sheet.info}")
    columns: dict[str, int] = {}
    first_row = sheet.iloc[0]

    for col, cell in first_row.items():
        if str(cell).startswith("Семестр"):
            columns[col] = int(cell[-1:-2:-1])

    return columns


def _parse_disciplines(
        sheet: DataFrame,
        columns: dict[str, int],
        column_name: str,
        exclude: str
) -> dict[str, list[int]]:
    log.info(f"Parsing disciplines information from sheet: {sheet.info}")
    disciplines: dict[str, list[int]] = {}

    for i, row in sheet.iterrows():
        if i < 2:
            continue

        discipline = ""
        term = ""

        for col, cell in row.items():
            col = str(col)

            if col == column_name:
                if isnull(cell) or cell.startswith(exclude):
                    continue
                discipline = cell

            if col in columns and not isnull(cell):
                term = columns[col]

        if not isnull(discipline) and discipline != "" and term != "":
            disciplines.setdefault(discipline, [])
            disciplines[discipline].append(term)

    return disciplines
