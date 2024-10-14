from fastapi import FastAPI

from .router import excel_router

app = FastAPI()

app.include_router(excel_router.router)
