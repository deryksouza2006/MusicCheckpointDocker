# 02-subir-banco.ps1
# Cria e executa o container PostgreSQL em segundo plano.
# Caso o container ja exista, apenas inicia ou informa que ja esta rodando.

$containerName = "cp2_rm563412_db"

$containerExists = docker ps -a --format "{{.Names}}" | Where-Object { $_ -eq $containerName }
$containerRunning = docker ps --format "{{.Names}}" | Where-Object { $_ -eq $containerName }

if ($containerRunning) {
    Write-Host "O container $containerName ja esta em execucao."
}
elseif ($containerExists) {
    Write-Host "O container $containerName ja existe, iniciando..."
    docker start $containerName
}
else {
    Write-Host "Criando e iniciando o container $containerName..."

    docker run -d `
      --name cp2_rm563412_db `
      --network cp2_rm563412_network `
      -p 5432:5432 `
      -e POSTGRES_DB=cp2db `
      -e POSTGRES_USER=cp2user `
      -e POSTGRES_PASSWORD=cp2pass `
      -v cp2_rm563412_pgdata:/var/lib/postgresql/data `
      postgres:16
}

Write-Host ""
Write-Host "Containers em execucao:"
docker ps