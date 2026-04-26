# 01-criar-rede-volume.ps1
# Cria a rede Docker e o volume nomeado do banco PostgreSQL.
# Caso já existam, apenas informa e continua.

$networkName = "cp2_rm563412_network"
$volumeName = "cp2_rm563412_pgdata"

$networkExists = docker network ls --format "{{.Name}}" | Where-Object { $_ -eq $networkName }

if ($networkExists) {
    Write-Host "A rede $networkName ja existe."
} else {
    docker network create $networkName
}

$volumeExists = docker volume ls --format "{{.Name}}" | Where-Object { $_ -eq $volumeName }

if ($volumeExists) {
    Write-Host "O volume $volumeName ja existe."
} else {
    docker volume create $volumeName
}

Write-Host ""
Write-Host "Redes Docker:"
docker network ls

Write-Host ""
Write-Host "Volumes Docker:"
docker volume ls