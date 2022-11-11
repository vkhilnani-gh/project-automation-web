package com.company.coreortest.web.ui.enums;


public enum ScrollDirections {
    UP("Up"),
    DOWN("Down"),
    RIGHT("Right"),
    LEFT("Left");

    private String scrollDirections;

    /**
     * Initialise ScrollDirection Enum
     * @param scrollDirection as String - direction to scroll
     */
    ScrollDirections(String scrollDirection) {
        this.scrollDirections = scrollDirection;
    }

    /**
     * @return String - the scroll direction associated with the ENUM value.
     */
    public String getScrollDirections() {
        return scrollDirections;
    }
}
