## Infraestrutura e CI/CD
O backend do PlacaPic executa em uma instancia do EC2
Para acesso temos as portas 8080 e 22 liberadas no Security Group para acesso a aplicação e a instância em si via SSH respectivamente
A configuração da VM consiste no key pair através do qual nos conectamos via SSH via PuTTY para validar as configurações como versão da JDK bem como a execução do agente do CodeDeploy. Para automatizar tais instalações na instância usamos o script de User data no qual acrescentamos comandos bash para instalação de software e utilitários necessários como o wget, o agente do codedeploy, a JDK e a variável de ambiente do codeartifact
```sh
#!/bin/bash
sudo yum update -y
sudo yum install -y ruby wget
sudo yum install wget
cd /home/ec2-user
wget https://aws-codedeploy-sa-east-1.s3.sa-east-1.amazonaws.com/latest/install
sudo chmod +x ./install
sudo ./install auto
sudo service codedeploy-agent start
wget https://corretto.aws/downloads/latest/amazon-corretto-11-x64-linux-jdk.tar.gz
tar xvf amazon-corretto-11-x64-linux-jdk.tar.gz
sudo mv amazon-corretto-11.0.15.9.1-linux-x64 /opt/
JAVA_HOME=/opt/amazon-corretto-11.0.15.9.1-linux-x64
echo "export PATH=${JAVA_HOME}/bin:${PATH}" >> /etc/profile
```
Após a inicialização da instância fazemos o acesso via SSH e confirmamos o sucesso da instalação por meio dos comandos
```sh
sudo service codedeploy-agent status
java -version
```
A Role do IAM associada a instância tem os seguintes acessos: SSM para pegar as configurações no Parameter Store, S3, CodeDeploy e CodeArtifact

O Security Group do RDS deve permitir o IP da instância EC2

No diretório raiz temos o arquivo appspec.yml
Ele é o responsável por executar os scripts relacionados ao funcionamento da aplicação dentro da VM
Ele está associado a fase do CodePipeline referente ao CodeDeploy que por sua vez é acionado após a fase de build, ele pega os artefatos gerados pelo build como o jar do Spring Boot e os scripts sh e faz a atribuição de permissões de execução via chmod, a remoção dos artefatos antigos de build anterior do diretório server e a inicialização do Spring Boot por meio do jar
O buildspec.yml por sua vez, também pode ser encontrado no diretório raiz do projeto e é responsável pela etapa de build por meio do CodeBuild
Nele configuramos a versão do java utilizada pelo ambiente de build bem como o comando de build em si da aplicação que no caso utiliza o gradle
Após execução dos testes e geração do artefato jar os arquivos listados na sessão files são posteriormente enviados para a instância através do CodeDeploy e baseado na configuração do appspec conforme mencionado anteriormente
