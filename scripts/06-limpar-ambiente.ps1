# 06-limpar-ambiente.ps1
# Remove os containers, rede e volume do projeto.
# Atenção: remover o volume apaga os dados persistidos do banco.

docker rm -f cp2_rm563412_api
docker rm -f cp2_rm563412_db

docker network rm cp2_rm563412_network

docker volume rm cp2_rm563412_pgdata

docker ps -a
docker volume ls
docker network ls