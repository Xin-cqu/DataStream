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

public interface SinkPrx extends Ice.ObjectPrx
{
    public void finalizeTransport(boolean now, Source theSource, Components.TransportProfileHolder tranPro)
        throws GenericError;

    public void finalizeTransport(boolean now, Source theSource, Components.TransportProfileHolder tranPro, java.util.Map<String, String> __ctx)
        throws GenericError;

    public Ice.AsyncResult begin_finalizeTransport(boolean now, Source theSource);

    public Ice.AsyncResult begin_finalizeTransport(boolean now, Source theSource, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_finalizeTransport(boolean now, Source theSource, Ice.Callback __cb);

    public Ice.AsyncResult begin_finalizeTransport(boolean now, Source theSource, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_finalizeTransport(boolean now, Source theSource, Callback_Sink_finalizeTransport __cb);

    public Ice.AsyncResult begin_finalizeTransport(boolean now, Source theSource, java.util.Map<String, String> __ctx, Callback_Sink_finalizeTransport __cb);

    public void end_finalizeTransport(Components.TransportProfileHolder tranPro, Ice.AsyncResult __result)
        throws GenericError;

    public void modifyTransport(Components.TransportProfile tpformodify)
        throws GenericError;

    public void modifyTransport(Components.TransportProfile tpformodify, java.util.Map<String, String> __ctx)
        throws GenericError;

    public Ice.AsyncResult begin_modifyTransport(Components.TransportProfile tpformodify);

    public Ice.AsyncResult begin_modifyTransport(Components.TransportProfile tpformodify, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_modifyTransport(Components.TransportProfile tpformodify, Ice.Callback __cb);

    public Ice.AsyncResult begin_modifyTransport(Components.TransportProfile tpformodify, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_modifyTransport(Components.TransportProfile tpformodify, Callback_Sink_modifyTransport __cb);

    public Ice.AsyncResult begin_modifyTransport(Components.TransportProfile tpformodify, java.util.Map<String, String> __ctx, Callback_Sink_modifyTransport __cb);

    public void end_modifyTransport(Ice.AsyncResult __result)
        throws GenericError;

    public void releaseTransport();

    public void releaseTransport(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_releaseTransport();

    public Ice.AsyncResult begin_releaseTransport(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_releaseTransport(Ice.Callback __cb);

    public Ice.AsyncResult begin_releaseTransport(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_releaseTransport(Callback_Sink_releaseTransport __cb);

    public Ice.AsyncResult begin_releaseTransport(java.util.Map<String, String> __ctx, Callback_Sink_releaseTransport __cb);

    public void end_releaseTransport(Ice.AsyncResult __result);

    public void startStream(PreviouAction previous, byte[] theMarker, StreamProfile sp, Components.TransportProfile tp)
        throws GenericError;

    public void startStream(PreviouAction previous, byte[] theMarker, StreamProfile sp, Components.TransportProfile tp, java.util.Map<String, String> __ctx)
        throws GenericError;

    public Ice.AsyncResult begin_startStream(PreviouAction previous, byte[] theMarker, StreamProfile sp, Components.TransportProfile tp);

    public Ice.AsyncResult begin_startStream(PreviouAction previous, byte[] theMarker, StreamProfile sp, Components.TransportProfile tp, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_startStream(PreviouAction previous, byte[] theMarker, StreamProfile sp, Components.TransportProfile tp, Ice.Callback __cb);

    public Ice.AsyncResult begin_startStream(PreviouAction previous, byte[] theMarker, StreamProfile sp, Components.TransportProfile tp, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_startStream(PreviouAction previous, byte[] theMarker, StreamProfile sp, Components.TransportProfile tp, Callback_Sink_startStream __cb);

    public Ice.AsyncResult begin_startStream(PreviouAction previous, byte[] theMarker, StreamProfile sp, Components.TransportProfile tp, java.util.Map<String, String> __ctx, Callback_Sink_startStream __cb);

    public void end_startStream(Ice.AsyncResult __result)
        throws GenericError;

    public void finishStream(byte[] markerForFinish)
        throws GenericError;

    public void finishStream(byte[] markerForFinish, java.util.Map<String, String> __ctx)
        throws GenericError;

    public Ice.AsyncResult begin_finishStream(byte[] markerForFinish);

    public Ice.AsyncResult begin_finishStream(byte[] markerForFinish, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_finishStream(byte[] markerForFinish, Ice.Callback __cb);

    public Ice.AsyncResult begin_finishStream(byte[] markerForFinish, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_finishStream(byte[] markerForFinish, Callback_Sink_finishStream __cb);

    public Ice.AsyncResult begin_finishStream(byte[] markerForFinish, java.util.Map<String, String> __ctx, Callback_Sink_finishStream __cb);

    public void end_finishStream(Ice.AsyncResult __result)
        throws GenericError;

    public void abortStream(byte[] markerForAbort)
        throws GenericError;

    public void abortStream(byte[] markerForAbort, java.util.Map<String, String> __ctx)
        throws GenericError;

    public Ice.AsyncResult begin_abortStream(byte[] markerForAbort);

    public Ice.AsyncResult begin_abortStream(byte[] markerForAbort, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_abortStream(byte[] markerForAbort, Ice.Callback __cb);

    public Ice.AsyncResult begin_abortStream(byte[] markerForAbort, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_abortStream(byte[] markerForAbort, Callback_Sink_abortStream __cb);

    public Ice.AsyncResult begin_abortStream(byte[] markerForAbort, java.util.Map<String, String> __ctx, Callback_Sink_abortStream __cb);

    public void end_abortStream(Ice.AsyncResult __result)
        throws GenericError;
}
