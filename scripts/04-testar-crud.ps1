# 04-testar-crud.ps1
# Testa o CRUD da API e comprova as alteracoes diretamente no banco PostgreSQL.

$nomeInicial = "CHECKPOINT_DOCKER"
$nomeAlterado = "CHECKPOINT_DOCKER_ALTERADO"

Write-Host ""
Write-Host "GET inicial em /genres"
Invoke-RestMethod -Method Get -Uri "http://localhost:8080/genres" | Out-Null
Write-Host "API respondeu com sucesso."

Write-Host ""
Write-Host "INSERT - criando registro $nomeInicial pela API"
$body = @{ name = $nomeInicial } | ConvertTo-Json
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/genres" -ContentType "application/json" -Body $body | Out-Null

Write-Host ""
Write-Host "SELECT no banco mostrando o registro criado:"
docker exec cp2_rm563412_db psql -U cp2user -d cp2db -c "select * from genre where name='$nomeInicial';"

$id = docker exec cp2_rm563412_db psql -U cp2user -d cp2db -t -A -c "select id from genre where name='$nomeInicial' order by id desc limit 1;"

Write-Host ""
Write-Host "ID criado: $id"

Write-Host ""
Write-Host "UPDATE - alterando registro pela API"
$body = @{ name = $nomeAlterado } | ConvertTo-Json
Invoke-RestMethod -Method Patch -Uri "http://localhost:8080/genres/$id" -ContentType "application/json" -Body $body | Out-Null

Write-Host ""
Write-Host "SELECT no banco mostrando o registro alterado:"
docker exec cp2_rm563412_db psql -U cp2user -d cp2db -c "select * from genre where id=$id;"

Write-Host ""
Write-Host "DELETE - removendo registro pela API"
Invoke-RestMethod -Method Delete -Uri "http://localhost:8080/genres/$id" | Out-Null

Write-Host ""
Write-Host "SELECT no banco mostrando que o registro foi removido:"
docker exec cp2_rm563412_db psql -U cp2user -d cp2db -c "select * from genre where id=$id;"