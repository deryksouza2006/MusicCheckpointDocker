# 03-subir-api.ps1
# Executa a API Java em um container Maven conectado ao banco PostgreSQL.
# Caso o container ja exista, apenas inicia ou informa que ja esta rodando.

$containerName = "cp2_rm563412_api"
$projectDir = (Get-Location).Path

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
      --name cp2_rm563412_api `
      --network cp2_rm563412_network `
      -p 8080:8080 `
      -e SPRING_DATASOURCE_URL=jdbc:postgresql://cp2_rm563412_db:5432/cp2db `
      -e SPRING_DATASOURCE_USERNAME=cp2user `
      -e SPRING_DATASOURCE_PASSWORD=cp2pass `
      -v "${projectDir}:/app" `
      -w /app `
      maven:3.9-eclipse-temurin-17 `
      bash -c "mvn clean package -DskipTests && java -jar target/*.jar"
}

Write-Host ""
Write-Host "Containers em execucao:"
docker ps