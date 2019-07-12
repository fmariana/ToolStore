package main.java.com.cardinalfinancial.programmingdemo.tooltypes;

/**
 * Factory for creating tool types
 */
public class ToolTypeFactory {
    /**
     * Returns instance of tooltype from string
     * @param toolType tool type as string. chainsaw ladder or jackhammer
     * @return tooltype instance
     */
    public ToolType getToolType(String toolType) {
        if (toolType == null || toolType.equals("")) {
            return null;
        } else if (toolType.equalsIgnoreCase(ChainsawToolType.CHAINSAW_TYPE_STRING)) {
            return new ChainsawToolType();
        } else if (toolType.equalsIgnoreCase(LadderToolType.LADDER_TYPE_STRING)) {
            return  new LadderToolType();
        } else if (toolType.equalsIgnoreCase(JackhammerToolType.JACKHAMMER_TYPE_STRING)) {
            return new JackhammerToolType();
        }
        return null;
    }
}
