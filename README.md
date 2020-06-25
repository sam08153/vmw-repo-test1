

# vmw-repo-test1

clone the repo
1. run - clone the repo - git clone https://github.com/sam08153/vmw-repo-test1.git

switch to repo
2. run - cd vmw-repo-test1

3. Instruction to run app locally

 To run the Dev locally - 
	mvn spring-boot:run
	
   then browse to API below

- POST - generate task- http://localhost:16000/api/generate
        curl --location --request POST 'localhost:16000/api/generate' \
	  --header 'Content-Type: application/json' \
	  --data-raw '{
		"goal": 10000,
		"step": 2
	   }'
	
- GET- getTask using UUID - http://localhost:16000/api/tasks/{UUID}/status
	
	curl --location --request GET 'localhost:16000/api/tasks/55e995d9-b603-11ea-8d14-ef9a563bdfe6/status?action=get_numlist'
	
- GET - getResult using UUID - http://localhost:16000/api/tasks/{UUID}/status?action=get_numlist
	
	curl --location --request GET 'localhost:16000/api/tasks/f9257fb8-b604-11ea-b99e-27676a1399dc/status?action=get_numlist' \
   	--header 'Content-Type: application/json' 
	

4. Instruction for running the Build

	run -  mvn clean package spring-boot:repackage
	
	run - jar -xf target/task1API-0.0.1-SNAPSHOT.jar
	
	then browse to API below
- POST - generate task- http://localhost:16000/api/generate
        curl --location --request POST 'localhost:16000/api/generate' \
	  --header 'Content-Type: application/json' \
	  --data-raw '{
		"goal": 10000,
		"step": 2
	   }'
	
- GET- getTask using UUID - http://localhost:16000/api/tasks/{UUID}/status
	
	curl --location --request GET 'localhost:16000/api/tasks/55e995d9-b603-11ea-8d14-ef9a563bdfe6/status?action=get_numlist'
	
- GET - getResult using UUID - http://localhost:16000/api/tasks/{UUID}/status?action=get_numlist
	
	curl --location --request GET 'localhost:16000/api/tasks/f9257fb8-b604-11ea-b99e-27676a1399dc/status?action=get_numlist' \
   	--header 'Content-Type: application/json' 

