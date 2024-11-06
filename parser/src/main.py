from contextlib import asynccontextmanager

from fastapi import FastAPI

from .messaging.producer import producer
from .router import excel_router


@asynccontextmanager
async def lifespan(app: FastAPI):
    await producer.start()
    yield
    await producer.stop()


app = FastAPI(lifespan=lifespan)

app.include_router(excel_router.router)
