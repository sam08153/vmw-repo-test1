

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
	- GET- getTask using UUID - http://localhost:16000/api/tasks/{UUID}/status
	- GET - getResult using UUID - http://localhost:16000/api/tasks/{UUID}/status?action=get_numlist
	

4. Instruction for running the Build

	run -  mvn clean package spring-boot:repackage
	
	run - jar -xf target/task1API-0.0.1-SNAPSHOT.jar
	
	then browse to API below
   	- POST - generate task- http://localhost:16000/api/generate
	- GET - getTask using UUID - http://localhost:16000/api/tasks/{UUID}/status
	- GET - getResult using UUID - http://localhost:16000/api/tasks/{UUID}/status?action=get_numlist
