/*
 * This file is part of PaperNicks, licensed under the MIT License.
 *
 * Copyright (c) 2021 Majekdor
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.majek.nicks.api;

import dev.majek.nicks.Nicks;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Handles the Nicks API. Contains some useful utility methods.
 */
public class NicksApi {

  /**
   * Shortcut for calling a Bukkit event.
   *
   * @param event The event to call.
   * @since 1.0.0
   */
  public void callEvent(@NotNull Event event) {
    Nicks.core().getServer().getPluginManager().callEvent(event);
    Nicks.debug("Called event " + event.getEventName());
  }

  /**
   * Get the {@link OfflinePlayer} a nickname belongs to from the nickname string.
   *
   * @param nickname The nickname. If you want to call this with a {@link Component} as the param,
   *                 use {@link PlainTextComponentSerializer} to get a string.
   * @return The {@link OfflinePlayer} if found.
   * @since 1.0.0
   */
  @Nullable
  public OfflinePlayer playerFromNick(@NotNull String nickname) {
    Iterator<Map.Entry<UUID, Component>> iterator = Nicks.core().getNickMap()
        .entrySet().stream().iterator();
    while (iterator.hasNext()) {
      Map.Entry<UUID, Component> next = iterator.next();
      if (PlainTextComponentSerializer.plainText().serialize(next.getValue())
          .equalsIgnoreCase(nickname)) {
        return Bukkit.getOfflinePlayer(next.getKey());
      }
    }
    return null;
  }
}