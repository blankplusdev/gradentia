package coursetool.render;

import java.io.IOException;
import java.util.ArrayList;

public class PrintHandler
{
    int frameWidth;
    int frameHeight;
    String standardSpacer;
    String standardHorizontalLine;

    ArrayList<String> printBuffer;

    /* Frame Symbols:

    ┌ ┐ └ ┘ ─ │ ├ ┤ ┬ ┴ ┼

    https://waylonwalker.com/drawing-ascii-boxes/##%E2%94%AC
    */

    public static void main(String[] args) throws IOException
    {
        
        clearConsole();
        PrintHandler draw = new PrintHandler(258,58);
        draw.drawFrame();
        draw.drawLogo(1, 5,true);
        draw.drawHLine(6, 0, draw.frameWidth);
        draw.pushBuffer();
    }

    public PrintHandler()
    {
        this(32,12);
    }

    public PrintHandler(int frameWidth, int frameHeight)
    {
        this.frameHeight = frameHeight;
        this.printBuffer = new ArrayList<String>();
        this.frameWidth = frameWidth;
        String newSpacer = "";
        for(int i = 0; i < (this.frameWidth-2); i++)
        {
            newSpacer += " ";
        }
        this.standardSpacer = new String(newSpacer);
        this.standardHorizontalLine = new String(makeNewHorizontalLine(this.frameWidth-2));
    }

    public void pushBuffer()
    {
        for(String line : this.printBuffer)
        {
            System.out.println(line);
        }
    }
    
    public void flushBuffer()
    {
        pushBuffer();
        this.printBuffer = new ArrayList<String>();
    }

    public void drawFrame()
    {
        this.printBuffer.add("┌"+this.standardHorizontalLine+"┐");
        for(int i = 0; i < (this.frameHeight-2); i++)
        {
            this.printBuffer.add("│"+this.standardSpacer+"│");
        }
        this.printBuffer.add("└"+this.standardHorizontalLine+"┘");
    }

    private void updateBufferWithElement(ArrayList<String> newElement, int line, int pos)
    {
        for(String elementLine : newElement)
        {
            //bufferReplaceAtPos(elementLine, line, pos);
            
            if(pos+elementLine.length() > this.frameWidth)
            {
            elementLine = elementLine.substring(0,(this.frameWidth-pos));
            }
        
            String sourceLine = this.printBuffer.get(line);
            String modifiedLine = sourceLine.substring(0,pos)+elementLine+sourceLine.substring(pos+(elementLine.length()));
            this.printBuffer.set(line,modifiedLine);
            line++;
        }   
    }

    /* Single line modification
    private void bufferReplaceAtPos(String newString, int line, int pos)
    {
        if(pos+newString.length() > this.frameWidth)
        {
            newString = newString.substring(0,(this.frameWidth-pos));
        }
        
        String sourceLine = this.printBuffer.get(line);
        String modifiedLine = sourceLine.substring(0,pos)+newString+sourceLine.substring(pos+(newString.length()));
        this.printBuffer.set(line,modifiedLine);
    }
    */

    public void drawBox(int line, int pos, int width, int height)
    {      
        if(width < 2 || height < 2)
        {
            //log error: too small box
            return; //fail fast
        }
        
        ArrayList<String> newBox = new ArrayList<String>();
        String boxHLine = makeNewHorizontalLine(width-2);
        String boxELine = makeNewEmptyLine(width-2);
        newBox.add("┌"+boxHLine+"┐");
        for(int i = 0; i < (height-2); i++)
        {
            newBox.add("│"+boxELine+"│");
        }
        newBox.add("└"+boxHLine+"┘");

        updateBufferWithElement(newBox, line, pos);
    }

    private static String makeNewHorizontalLine(int width)
    {
        String hLine = "";
        for(int i = 0; i < width; i++)
        {
            hLine += "─";
        }
        return hLine;
    }

    private static String makeNewEmptyLine(int width)
    {
        String eLine = "";
        for(int i = 0; i < width; i++)
        {
            eLine += " ";
        }
        return eLine;
    }

    public void drawHLine(int line, int startPos, int endPos)
    {
        endPos = Math.min(endPos,this.frameWidth);
        int length = (endPos-startPos);
        
        String newLine = makeNewHorizontalLine(length);
        for(int i = startPos; i < endPos; i++)
        {
            String checkStr = this.printBuffer.get(line).substring(i,i+1);
            String replaceStr = null;
            boolean isStart = (i == startPos);
            boolean isEnd = (i == (endPos-1));
            
            switch(checkStr)
            {
                case "│":
                if(isStart) {replaceStr = "├";}
                else if(isEnd) {replaceStr = "┤";}
                else {replaceStr = "┼";}
                break;
                
                case "▄":
                if(isStart) {replaceStr = "┌";}
                else if(isEnd) {replaceStr = "┐";}
                else {replaceStr = "┬";}
                
                break;
                case "┌":
                if(!isStart) replaceStr = "┬"; 
                break;
                case "┐":
                if(!isEnd) replaceStr = "┬"; 
                break;

                case "▀":
                if(isStart) {replaceStr = "└";}
                else if(isEnd) {replaceStr = "┘";}
                else {replaceStr = "┴";} 
                break;
                case "└":
                if(!isStart) replaceStr = "┴"; 
                break;
                case "┘":
                if(!isEnd) replaceStr = "┴"; 
                break;

                case " ":
                if(isStart) replaceStr = "▐";
                else if(isEnd) replaceStr = "▌";
                break;
            }

            if(replaceStr != null)
            {
                newLine = newLine.substring(0,i)+replaceStr+newLine.substring(i+1);
            }
        }

        ArrayList<String> newLineElement = new ArrayList<>(1);
        newLineElement.add(newLine);
        updateBufferWithElement(newLineElement, line, startPos);
    }
    
    public void drawVLine(int pos, int startLine, int endLine){}

    public void drawLogo(int line, int pos, boolean showName)
    {
        String[] logo = {"   ______               __           __  _","  / ____/________ _____/ /__  ____  / /_(_)___ _"," / / __/ ___/ __ `/ __  / _ \\/ __ \\/ __/ / __ `/","/ /_/ / /  / /_/ / /_/ /  __/ / / / /_/ / /_/ /","\\____/_/   \\__,_/\\__,_/\\___/_/ /_/\\__/_/\\__,_/"};
        ArrayList<String> logoElement = new ArrayList<String>(5);
        for(int i = 0; i < logo.length; i++)
        {
            String newLine = logo[i];
            if(i == 4 && showName) {newLine += "     by Niko B.";}
            logoElement.add(newLine);
        }
        updateBufferWithElement(logoElement, line, pos);

        /*
        "   ______               __           __  _"
        "  / ____/________ _____/ /__  ____  / /_(_)___ _"
        " / / __/ ___/ __ `/ __  / _ \/ __ \/ __/ / __ `/"
        "/ /_/ / /  / /_/ / /_/ /  __/ / / / /_/ / /_/ /"
        "\____/_/   \__,_/\__,_/\___/_/ /_/\__/_/\__,_/"  
        */                                                 
    }

    public void drawText(String text, int line, int pos)
    {
        ArrayList<String> newTextElement = new ArrayList<String>(1);
        newTextElement.add(text);
        updateBufferWithElement(newTextElement, line, pos);
    }

    public void drawAboutInfo()
    {
        
    }

    public static void clearConsole()
    {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Error clearing console: " + e.getMessage());
        }
    }
}