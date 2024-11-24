from contextlib import asynccontextmanager

from fastapi import FastAPI
from starlette.middleware.cors import CORSMiddleware

from .messaging.producer import producer
from .router import excel_router


@asynccontextmanager
async def lifespan(app: FastAPI):
    await producer.start()
    yield
    await producer.stop()


app = FastAPI(lifespan=lifespan)

origins = [
    "http://localhost:5173",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

app.include_router(excel_router.router)
