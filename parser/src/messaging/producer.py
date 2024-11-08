import asyncio
import json
import logging
import os

from aiokafka import AIOKafkaProducer

from ..schema.models import Student, Discipline

loop = asyncio.get_event_loop()

STUDENT_KAFKA_TOPIC = os.getenv("KAFKA_TOPIC_1", "student")
DISCIPLINE_KAFKA_TOPIC = os.getenv("KAFKA_TOPIC_2", "discipline")
KAFKA_BOOTSTRAP_SERVERS = os.getenv("KAFKA_BOOTSTRAP_SERVERS", "localhost:29092")

producer = AIOKafkaProducer(
    loop=loop,
    bootstrap_servers=KAFKA_BOOTSTRAP_SERVERS,
    enable_idempotence=True
)

log = logging.getLogger("uvicorn.error")


async def send_student(student: Student, major: str):
    log.info(f"Sending student with name: {student.full_name} to topic {STUDENT_KAFKA_TOPIC}")
    await producer.send(
        topic=STUDENT_KAFKA_TOPIC,
        key=json.dumps(major).encode("utf-8"),
        value=json.dumps(student.dict()).encode("utf-8")
    )


async def send_discipline(discipline: Discipline, major: str):
    log.info(f"Sending discipline message with name: {discipline.name} to topic {DISCIPLINE_KAFKA_TOPIC}")
    await producer.send(
        topic=DISCIPLINE_KAFKA_TOPIC,
        key=json.dumps(major).encode("utf-8"),
        value=json.dumps(discipline.dict()).encode("utf-8")
    )
