package com.github.ptlthg.authtester;

import com.github.ptlthg.authtester.commands.TestAuthCommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "authtester", useMetadata=true)
public class AuthTester {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new TestAuthCommand());
    }
}
