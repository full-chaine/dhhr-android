package com.codelines.lib.dynamiclist;

public class AdapterItem<T>
{
    public final ViewType viewType;
    public final T value;
    public final Object tag;
    public final String section;

    public AdapterItem(ViewType type)
    {
        this(type, null, null, "");
    }

    public AdapterItem(T value, Object tag)
    {
        this(ViewType.Data, value, tag, "");
    }

    private AdapterItem(ViewType type, T value, Object tag, String section)
    {
        this.viewType = type;
        this.value = value;
        this.tag = tag;
        this.section = section;        
    }
}
