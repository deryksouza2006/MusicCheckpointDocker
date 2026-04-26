# 05-evidencias-docker.ps1
# Comandos de evidencia exigidos no Checkpoint.

Write-Host ""
Write-Host "Containers em execucao:"
docker ps --format "table {{.Names}}\t{{.Image}}\t{{.Status}}\t{{.Ports}}"

Write-Host ""
Write-Host "Imagens Docker:"
docker image ls

Write-Host ""
Write-Host "Volumes Docker:"
docker volume ls

Write-Host ""
Write-Host "Redes Docker:"
docker network ls