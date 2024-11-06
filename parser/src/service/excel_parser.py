import logging
from io import BytesIO

import pandas as pd
from pandas import DataFrame

log = logging.getLogger("uvicorn.error")


def parse_all_sheets(file: BytesIO) -> dict[str, DataFrame]:
    log.info(f"Parsing all sheets from the excel file")
    return pd.read_excel(file, sheet_name=None, engine="openpyxl")


def parse_first_sheet(file: BytesIO) -> DataFrame:
    log.info(f"Parsing first sheet from the excel file")
    return pd.read_excel(file, engine="openpyxl")


def parse_sheet(file: BytesIO, sheet_name: str = None) -> DataFrame:
    log.info(f"Parsing sheet {sheet_name} from the excel file")
    return pd.read_excel(file, sheet_name=sheet_name, engine="openpyxl")
