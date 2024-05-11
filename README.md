# 

## Model
www.msaez.io/#/storming/industrialaccident-egovk

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- accident
- assessment
- compensation
- statistics
- notification


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- accident
```
 http :8088/accidents id="id" businessCode="businessCode" employeeId="employeeId" name="name" hospitalCode="hospitalCode" period="period" doctorNote="doctorNote" accidentType="accidentType" status="status" applyDt="applyDt" 
```
- assessment
```
 http :8088/assessments id="id" accidentId="accidentId" businessCode="businessCode" employeeId="employeeId" assessorId="assessorId" hospitalCode="hospitalCode" doctorNote="doctorNote" results="results" date="date" comments="comments" 
 http :8088/sickLeaves id="id" accessmentId="accessmentId" accidentId="accidentId" businessCode="businessCode" employeeId="employeeId" averageSalary="averageSalary" period="period" status="status" date="date" 
```
- compensation
```
 http :8088/compensations id="id" sickLeaveId="sickLeaveId" assessmentId="assessmentId" accidentId="accidentId" employeeId="employeeId" amount="amount" method="method" date="date" status="status" 
```
- statistics
```
 http :8088/statistics id="id" accidentId="accidentId" hospitalCode="hospitalCode" businessCode="businessCode" results="results" date="date" 
```
- notification
```
 http :8088/notifications id="id" receiver="receiver" msg="msg" date="date" 
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

