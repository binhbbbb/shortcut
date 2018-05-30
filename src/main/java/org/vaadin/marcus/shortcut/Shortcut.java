package org.vaadin.marcus.shortcut;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.dom.DomListenerRegistration;
import com.vaadin.flow.dom.Element;
import elemental.json.JsonObject;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Shortcut {

  /**
   * Add a keyboard shortcut to an Element.
   *
   * @param shortcutScope Listen for events on this Element and its children.
   * @param key           The shortcut key code. See {@link Key} for list.
   * @param listener      Callback function when shortcut is triggered.
   * @param modifiers     Optional modifier keys.  See {@link Key} for list.
   * @return {@link DomListenerRegistration} for unregistering shortcut.
   */
  public static DomListenerRegistration add(Element shortcutScope, String key, Listener listener, String... modifiers) {
    String modifierFilter = Arrays.stream(modifiers)
        .map(modifier -> "event.getModifierState('" + modifier + "')")
        .collect(Collectors.joining(" && "));

    String filter = "event.key === '" + key + "'" +
        " && " + (modifierFilter.isEmpty() ? "true" : modifierFilter);

    return shortcutScope.addEventListener("keydown", event -> listener.handleAction())
        .addEventData("event.keyCode")
        .addEventData("event.altKey")
        .addEventData("event.ctrlKey")
        .addEventData("event.metaKey")
        .addEventData("event.shiftKey")
        .setFilter(filter);
  }

  /**
   * Add a keyboard shortcut to a Component.
   *
   * @param shortcutScope Listen for events on this Component and its children.
   * @param key           The shortcut key code. See {@link Key} for list.
   * @param listener      Callback function when shortcut is triggered.
   * @param modifiers     Optional modifier keys. See {@link Key} for list.
   * @return {@link DomListenerRegistration} for unregistering shortcut.
   */
  public static DomListenerRegistration add(Component shortcutScope, String key, Listener listener, String... modifiers) {
    return add(shortcutScope.getElement(), key, listener, modifiers);
  }

  /**
   * Callback interface for keyboard shortcuts.
   */
  @FunctionalInterface
  public interface Listener extends Serializable {
    void handleAction();
  }
}
