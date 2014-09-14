// **********************************************************************
//
// Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.5.1
//
// <auto-generated>
//
// Generated from file `Components.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Components.Streams;

public enum PreviouAction implements java.io.Serializable
{
    
    NoAction(0),
    
    FinishStream(1),
    
    AbortStream(2);

    public int
    value()
    {
        return __value;
    }

    public static PreviouAction
    valueOf(int __v)
    {
        switch(__v)
        {
        case 0:
            return NoAction;
        case 1:
            return FinishStream;
        case 2:
            return AbortStream;
        }
        return null;
    }

    private
    PreviouAction(int __v)
    {
        __value = __v;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeEnum(value(), 2);
    }

    public static PreviouAction
    __read(IceInternal.BasicStream __is)
    {
        int __v = __is.readEnum(2);
        return __validate(__v);
    }

    private static PreviouAction
    __validate(int __v)
    {
        final PreviouAction __e = valueOf(__v);
        if(__e == null)
        {
            throw new Ice.MarshalException("enumerator value " + __v + " is out of range");
        }
        return __e;
    }

    private final int __value;
}