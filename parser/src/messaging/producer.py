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


async def send_student(student: Student):
    log.info(f"Sending student with name: {student.name} to topic {STUDENT_KAFKA_TOPIC}")
    await producer.send(
        STUDENT_KAFKA_TOPIC,
        json.dumps(student.dict()).encode("utf-8")
    )


async def send_discipline(discipline: Discipline):
    log.info(f"Sending discipline message with name: {discipline.name} to topic {DISCIPLINE_KAFKA_TOPIC}")
    await producer.send(
        DISCIPLINE_KAFKA_TOPIC,
        json.dumps(discipline.dict()).encode("utf-8")
    )
