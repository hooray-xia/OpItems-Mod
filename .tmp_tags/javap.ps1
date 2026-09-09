$nested = "c:\Users\xiaobao\Documents\MyFirstMod\.tmp_tags\META-INF\jars\fabric-creative-tab-api-v1-5.0.14+d871b99e9e.jar"
$cls = "net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents" + '$' + "ModifyOutput"
Write-Output "=== ModifyOutput ==="
& javap -p -classpath $nested $cls 2>&1 | ForEach-Object { Write-Output $_ }
Write-Output "=== FabricCreativeModeTabOutput ==="
& javap -p -classpath $nested net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput 2>&1 | ForEach-Object { Write-Output $_ }
