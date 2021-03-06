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

public interface SourcePrx extends Ice.ObjectPrx
{
    public byte[] abortStream();

    public byte[] abortStream(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_abortStream();

    public Ice.AsyncResult begin_abortStream(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_abortStream(Ice.Callback __cb);

    public Ice.AsyncResult begin_abortStream(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_abortStream(Callback_Source_abortStream __cb);

    public Ice.AsyncResult begin_abortStream(java.util.Map<String, String> __ctx, Callback_Source_abortStream __cb);

    public byte[] end_abortStream(Ice.AsyncResult __result);

    public void modifyTransport(Components.TransportProfile theProfile)
        throws GenericError;

    public void modifyTransport(Components.TransportProfile theProfile, java.util.Map<String, String> __ctx)
        throws GenericError;

    public Ice.AsyncResult begin_modifyTransport(Components.TransportProfile theProfile);

    public Ice.AsyncResult begin_modifyTransport(Components.TransportProfile theProfile, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_modifyTransport(Components.TransportProfile theProfile, Ice.Callback __cb);

    public Ice.AsyncResult begin_modifyTransport(Components.TransportProfile theProfile, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_modifyTransport(Components.TransportProfile theProfile, Callback_Source_modifyTransport __cb);

    public Ice.AsyncResult begin_modifyTransport(Components.TransportProfile theProfile, java.util.Map<String, String> __ctx, Callback_Source_modifyTransport __cb);

    public void end_modifyTransport(Ice.AsyncResult __result)
        throws GenericError;
}
