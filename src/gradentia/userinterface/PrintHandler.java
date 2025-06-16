package gradentia.userinterface;

import java.util.Scanner;

import gradentia.models.Course;

import java.io.IOException;
import java.util.ArrayList;

public class PrintHandler //For use with CMD interface
{
    private final static String[] logo = {"   ______               __           __  _","  / ____/________ _____/ /__  ____  / /_(_)___ _"," / / __/ ___/ __ `/ __  / _ \\/ __ \\/ __/ / __ `/","/ /_/ / /  / /_/ / /_/ /  __/ / / / /_/ / /_/ /","\\____/_/   \\__,_/\\__,_/\\___/_/ /_/\\__/_/\\__,_/","────────────────────────────────────────────────"};
    private int frameWidth;
    private int frameHeight;
    private String standardSpacer;
    private String standardHorizontalLine;

    ArrayList<String> printBuffer;

    /* Frame Symbols:

    ┌ ┐ └ ┘ ─ │ ├ ┤ ┬ ┴ ┼

    https://waylonwalker.com/drawing-ascii-boxes/##%E2%94%AC
    */

    public static void main(String[] args) throws IOException
    {
        Course calc = generateExampleCourse();
        Scanner userIn = new Scanner(System.in);
        clearConsole();
        PrintHandler draw = initDrawField(userIn);
        draw.drawFrame();
        draw.drawLogo(1, 5,true);
        draw.drawAboutInfo();
        draw.drawHLine(6, 0, draw.frameWidth);
        draw.drawVLine((draw.frameWidth-(draw.frameWidth/5)), 6, draw.frameHeight);
        draw.drawCourseCard(calc, 8, 2);
        draw.drawHLine(draw.frameHeight-7, 0, draw.frameWidth);
        
        
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
    
    public void flushBuffer(ArrayList<String> saveAL)
    {
        pushBuffer();
        
        if(saveAL != null)
        {
            saveAL = new ArrayList<String>(this.printBuffer);
        }
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
        //This is currently unsafe, I'd recomend adding complete* bound checks
        startPos = Math.max(startPos,0);
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

        ArrayList<String> newHLineElement = new ArrayList<>(1);
        newHLineElement.add(newLine);
        updateBufferWithElement(newHLineElement, line, startPos);
    }
    
    public void drawVLine(int pos, int startLine, int endLine)
    {
        int lineCount = (endLine-startLine);
        ArrayList<String> newVLineElement = new ArrayList<String>(lineCount);
        for(int i = startLine; i < endLine; i++)
        {
            String nextSegment = "│";
            
            String checkStr = this.printBuffer.get(i).substring(pos,pos+1);
            String replaceStr = null;
            boolean isStart = (i == startLine);
            boolean isEnd = (i == (endLine-1));
            
            switch(checkStr) //┌ ┐ └ ┘ ─ │ ├ ┤ ┬ ┴ ┼
            {
                case "─":
                if(isStart) {replaceStr = "┬";}
                else if(isEnd) {replaceStr = "┴";}
                else {replaceStr = "┼";}
                break;
                
                case "▌":
                if(isStart) {replaceStr = "┐";}
                else if(isEnd) {replaceStr = "┘";}
                else {replaceStr = "┤";}
                break;
                case "┌":
                if(!isStart) replaceStr = "├"; 
                break;
                case "┐":
                if(!isStart) replaceStr = "┤"; 
                break;

                case "▐":
                if(isStart) {replaceStr = "┌";}
                else if(isEnd) {replaceStr = "└";}
                else {replaceStr = "├";} 
                break;
                case "└":
                if(!isEnd) replaceStr = "├"; 
                break;
                case "┘":
                if(!isEnd) replaceStr = "┤"; 
                break;

                case " ":
                if(isStart) replaceStr = "▄";
                else if(isEnd) replaceStr = "▀";
                break;
            }

            if(replaceStr != null)
            {
                nextSegment = replaceStr;
            }

            newVLineElement.add(nextSegment);
        }

        updateBufferWithElement(newVLineElement, startLine, pos);
        
    }

    public void drawLogo(int line, int pos, boolean showName)
    {
        ArrayList<String> logoElement = new ArrayList<String>(5);
        for(int i = 0; i < logo.length-1; i++) //-1 Omits the additional vertical line
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
        String aboutVersion = "UI Draw Test" + ": " + "4"; //TODO: replace with fetch call from config/release file once it's implemented
        String aboutVersionDate = "Build Date: " + "June 14th, 2025"; //TODO: replace with fetch call from config/release file once it's implemented
        String aboutInstitution = "Institution: " + "N/A"; //TODO: replace with fetch call from Institution file once it's implemented

        drawText(aboutVersion,1,(this.frameWidth-2-aboutVersion.length()));
        drawText(aboutVersionDate,2,(this.frameWidth-2-aboutVersionDate.length()));
        drawText(aboutInstitution,5,(this.frameWidth-2-aboutInstitution.length()));
    }

    public void drawCourseCard(Course target, int line, int pos)
    {

        String code = target.getCode();
        String name = target.getName();
        String credit = "Cr Hrs: " + target.getCreditHours();

        int cardWidth = 6 + (Math.max((code.length() + credit.length() + 4),(name.length())));

        drawBox(line, pos, cardWidth, 7);
        drawText(code + "    " + credit, ++line, pos + 3);
        drawText(name, ++line,(pos + 3 + (cardWidth-6-name.length())/2));
    }

    public static Course generateExampleCourse()
    {
        Course example = new Course(1);
        example.setName("Calculus I");
        example.setCourseCode("MATH1151");
        example.setCreditHours(5);

        return example;
        
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

    public static PrintHandler initDrawField(Scanner inputScanner)
    {
        for(String line : logo)
        {
            System.out.println(line);
        }
        System.out.println("\n Press enter to continue...");
        pause();
        
        
        System.out.println("The CMD based UI relies upon no text wrapping to remain legible.\n\nWe ask that you resize the window as you desire now, and then enter\nthe width (in characters) that does not take more than one line.\n(Default is 200)\n");
        int desiredWidth = 200;
        boolean satisfactoryWidth = false;

        while(!satisfactoryWidth)
        {
            printWidth(desiredWidth);
            System.out.println("\n\n Is this width satisfactory? y/n\n");
            String userResponse = inputScanner.next().toLowerCase();
            if(userResponse.equals("y")||userResponse.equals("yes"))
            {
                satisfactoryWidth = true;
                continue;
            }
            else
            {
                System.out.println("\nPlease enter new desired width:\n");
                desiredWidth = inputScanner.nextInt();
                System.out.println();
            }
        }
        int setHeight = (int)((desiredWidth / 25.0)*6);
        System.out.println("\n Press enter to continue...");
        clearConsole();
        return new PrintHandler(desiredWidth,setHeight);
    }

    private static void printWidth(int width)
    {
        width = (width/10) * 10;
        
        for(int currentWidth = 10; currentWidth <= width ;currentWidth += 10)
        {
            String out = "<  ";
            if(currentWidth < 100){out += " ";}
            out += currentWidth+"   >";
            System.out.print(out);
        }
    }

    private static void pause()
{
    try {System.in.read();}
    catch(Exception e) {}
    return;
}

}

//"The CMD based UI relies upon no text wrapping to remain legible.\n\nWe ask that you resize the window as you desire now, and then enter\nthe width (in characters) that does not take more than one line.\n(Default is 200)"






