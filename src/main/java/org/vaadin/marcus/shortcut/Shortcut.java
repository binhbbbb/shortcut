package org.vaadin.marcus.shortcut;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.dom.Element;
import elemental.json.JsonObject;

import java.io.Serializable;
import java.util.Arrays;

public class Shortcut {

  /**
   * Add a keyboard shortcut to an Element.
   * @param shortcutScope Listen for events on this Element and its children.
   * @param key The shortcut key code. See {@link KeyCodes}.
   * @param listener Callback function when shortcut is triggered.
   * @param modifiers Optional modifier keys. See {@link Modifiers}
   */
  public static void add(Element shortcutScope, int key, Listener listener, String... modifiers) {
    shortcutScope.addEventListener("keydown", event -> {
      JsonObject eventData = event.getEventData();
      if (eventData.getNumber("event.keyCode") == key &&
          Arrays.stream(modifiers).allMatch(modifier ->
              eventData.getBoolean("event." + modifier))) {
        listener.handleAction();
      }
    })
        .addEventData("event.keyCode")
        .addEventData("event.altKey")
        .addEventData("event.ctrlKey")
        .addEventData("event.metaKey")
        .addEventData("event.shiftKey");
  }

  /**
   * Add a keyboard shortcut to a Component.
   * @param shortcutScope Listen for events on this Component and its children.
   * @param key The shortcut key code. See {@link KeyCodes}.
   * @param listener Callback function when shortcut is triggered.
   * @param modifiers Optional modifier keys. See {@link Modifiers}
   */
  public static void add(Component shortcutScope, int key, Listener listener, String... modifiers) {
    add(shortcutScope.getElement(), key, listener, modifiers);
  }

  /**
   * Callback interface for keyboard shortcuts.
   */
  @FunctionalInterface
  public interface Listener extends Serializable {
    void handleAction();
  }

  /**
   * Convenience list of key codes.
   */
  public interface KeyCodes {
    public static final int ENTER = 13;

    public static final int ESCAPE = 27;

    public static final int PAGE_UP = 33;

    public static final int PAGE_DOWN = 34;

    public static final int TAB = 9;

    public static final int ARROW_LEFT = 37;

    public static final int ARROW_UP = 38;

    public static final int ARROW_RIGHT = 39;

    public static final int ARROW_DOWN = 40;

    public static final int BACKSPACE = 8;

    public static final int DELETE = 46;

    public static final int INSERT = 45;

    public static final int END = 35;

    public static final int HOME = 36;

    public static final int F1 = 112;

    public static final int F2 = 113;

    public static final int F3 = 114;

    public static final int F4 = 115;

    public static final int F5 = 116;

    public static final int F6 = 117;

    public static final int F7 = 118;

    public static final int F8 = 119;

    public static final int F9 = 120;

    public static final int F10 = 121;

    public static final int F11 = 122;

    public static final int F12 = 123;

    public static final int A = 65;

    public static final int B = 66;

    public static final int C = 67;

    public static final int D = 68;

    public static final int E = 69;

    public static final int F = 70;

    public static final int G = 71;

    public static final int H = 72;

    public static final int I = 73;

    public static final int J = 74;

    public static final int K = 75;

    public static final int L = 76;

    public static final int M = 77;

    public static final int N = 78;

    public static final int O = 79;

    public static final int P = 80;

    public static final int Q = 81;

    public static final int R = 82;

    public static final int S = 83;

    public static final int T = 84;

    public static final int U = 85;

    public static final int V = 86;

    public static final int W = 87;

    public static final int X = 88;

    public static final int Y = 89;

    public static final int Z = 90;

    public static final int NUM0 = 48;

    public static final int NUM1 = 49;

    public static final int NUM2 = 50;

    public static final int NUM3 = 51;

    public static final int NUM4 = 52;

    public static final int NUM5 = 53;

    public static final int NUM6 = 54;

    public static final int NUM7 = 55;

    public static final int NUM8 = 56;

    public static final int NUM9 = 57;

    public static final int SPACEBAR = 32;
  }

  /**
   * List of Modifier keys.
   */
  public interface Modifiers {
    public static final String ALT = "altKey";
    public static final String CTRL = "ctrlKey";
    public static final String META = "metaKey";
    public static final String SHIFT = "shiftKey";
  }
}
