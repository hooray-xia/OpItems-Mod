$jar = "c:\Users\xiaobao\Documents\MyFirstMod\.gradle\loom-cache\minecraftMaven\net\minecraft\minecraft-merged-043a8b3edf\26.2\minecraft-merged-043a8b3edf-26.2.jar"
$tmp = "c:\Users\xiaobao\Documents\MyFirstMod\.tmp_src\eggs"
New-Item -ItemType Directory -Force -Path $tmp | Out-Null
Push-Location $tmp
& jar xf $jar "assets/minecraft/items" 2>&1 | Out-Null
$files = Get-ChildItem -Filter "*spawn_egg*" | ForEach-Object { $_.BaseName }
Pop-Location
$files | Sort-Object
