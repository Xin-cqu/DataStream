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

public interface _SourceOperations
{
    byte[] abortStream(Ice.Current __current);

    void modifyTransport(Components.TransportProfile theProfile, Ice.Current __current)
        throws GenericError;
}
