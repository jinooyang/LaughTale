FROM python:3.9.13
WORKDIR /app

COPY . .

RUN apt-get update -y && apt-get install -y libgl1-mesa-glx
RUN pip install --no-cache-dir -r requirements.txt

EXPOSE 8000

CMD ["uvicorn", "main:app","--host", "0.0.0.0", "--port", "8000"]
