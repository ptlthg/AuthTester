package com.github.ptlthg.authtester.commands;

import com.mojang.authlib.exceptions.AuthenticationException;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.UUID;

public class TestAuthCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "testauth";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        String serverId = args.length > 0 ? args[0] : null;

        if (serverId == null) {
            serverId = UUID.randomUUID().toString().replaceAll("-", "");
        }

        String commentForDecompilers = "This sends a request to Mojang's auth server, used for verification. This is how we verify you are the real user without your session details. This is the exact same system Skytils and Optifine uses.";

        try {
            Minecraft client = Minecraft.getMinecraft();
            client.getSessionService().joinServer(client.getSession().getProfile(), client.getSession().getToken(), serverId);

            sender.addChatMessage(new ChatComponentText("Sent join request to: " + serverId));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            sender.addChatMessage(new ChatComponentText("Error!"));
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
