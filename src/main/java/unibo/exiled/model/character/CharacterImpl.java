package unibo.exiled.model.character;

public abstract class CharacterImpl implements  Character{
    private final String path;
    private final String upImageName;
    private final String downImageName;
    private final String leftImageName;
    private final String rightImageName;

    protected CharacterImpl(final String path,
                         final String upImageName,
                         final String downImageName,
                         final String leftImageName,
                         final String rightImageName){
        this.path = path;
        this.upImageName = upImageName;
        this.downImageName = downImageName;
        this.leftImageName = leftImageName;
        this.rightImageName = rightImageName;
    }

    public String getImagePath(){return this.path;}
    public String getImageUpPath(){return this.upImageName;}
    public String getImageDownPath(){return this.downImageName;}
    public String getImageLeftPath(){return this.leftImageName;}
    public String getImageRightPath(){return this.rightImageName;}
}
