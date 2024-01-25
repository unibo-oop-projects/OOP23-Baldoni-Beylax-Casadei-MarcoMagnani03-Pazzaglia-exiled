package unibo.exiled.model.character;

import java.util.List;

public abstract class CharacterImpl implements  Character{
    private final String path;
    private final String upImageName;
    private final String downImageName;
    private final String leftImageName;
    private final String rightImageName;

    protected CharacterImpl(final List<String> paths){
        this.path = paths.get(0);
        this.upImageName = paths.get(1);
        this.downImageName = paths.get(2);
        this.leftImageName = paths.get(3);
        this.rightImageName = paths.get(4);
    }

    public String getImagePath(){return this.path;}
    public String getImageUpPath(){return this.upImageName;}
    public String getImageDownPath(){return this.downImageName;}
    public String getImageLeftPath(){return this.leftImageName;}
    public String getImageRightPath(){return this.rightImageName;}
}
