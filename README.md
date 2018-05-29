# Keyboard shortcut listener for [Vaadin Flow](https://vaadin.com/flow)

A simple helper for making it easier to add keyboard shortcuts to your Vaadin Flow applications.

## Event scoping

The component or element you pass in works as the scope for the event. This means that the event can come from either the component itself or any of it's children.


## Simple usage

Often all that is needed is mapping a key to an action.

```java
var messageField = new TextField();
Shortcut.add(messageField, Shortcut.KeyCodes.ENTER, sendButton::click);
```

## Modifier keys

You can also add modifier keys to the shortcuts. For example, submit on Shift+Enter:

```java
var messageField = new TextField();
Shortcut.add(messageField, Shortcut.KeyCodes.ENTER, sendButton::click, Shortcut.Modifiers.SHIFT);
```