package com.yxy.monitorthread;


import static com.yxy.monitorthread.ClassConstant.S_HandlerThread;
import static com.yxy.monitorthread.ClassConstant.S_ScheduledThreadPoolExecutor;
import static com.yxy.monitorthread.ClassConstant.S_TBaseHandlerThread;
import static com.yxy.monitorthread.ClassConstant.S_TBaseScheduledThreadPoolExecutor;
import static com.yxy.monitorthread.ClassConstant.S_TBaseThread;
import static com.yxy.monitorthread.ClassConstant.S_TBaseThreadPoolExecutor;
import static com.yxy.monitorthread.ClassConstant.S_TBaseTimer;
import static com.yxy.monitorthread.ClassConstant.S_Thread;
import static com.yxy.monitorthread.ClassConstant.S_ThreadPoolExecutor;
import static com.yxy.monitorthread.ClassConstant.S_Timer;
import static com.yxy.monitorthread.PluginUtils.log;

import com.android.ddmlib.Log;

import org.objectweb.asm.MethodVisitor;


public class ChangeSuperMethodVisitor extends MethodVisitor {
    private String className;

    ChangeSuperMethodVisitor(int api, MethodVisitor methodVisitor, String className) {
        super(api, methodVisitor);
        this.className = className;
        Log.i("tag5","ChangeSuperMethodVisitor");
        System.out.println("ChangeSuperMethodVisitor");
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        if (name.equalsIgnoreCase("<init>")) {
            switch (owner) {
                case S_Thread:
                    log("changingSuper Thread: " + className + " " + owner + " " + name);
                    mv.visitMethodInsn(opcode, S_TBaseThread, name, descriptor, false);
                    return;
                case S_ThreadPoolExecutor:
                    log("changingSuper ThreadPoolExecutor: " + className + " " + owner + " " + name);
                    mv.visitMethodInsn(opcode, S_TBaseThreadPoolExecutor, name, descriptor, false);
                    return;
                case S_ScheduledThreadPoolExecutor:
                    log("changingSuper ScheduledThreadPoolExecutor: " + className + " " + owner + " " + name);
                    mv.visitMethodInsn(opcode, S_TBaseScheduledThreadPoolExecutor, name, descriptor, false);
                    return;
                case S_Timer:
                    log("changingSuper Timer: " + className + " " + owner + " " + name);
                    mv.visitMethodInsn(opcode, S_TBaseTimer, name, descriptor, false);
                    return;
                case S_HandlerThread:
                    log("changingSuper HandlerThread: " + className + " " + owner + " " + name);
                    mv.visitMethodInsn(opcode, S_TBaseHandlerThread, name, descriptor, false);
                    return;
            }
        }
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }
}
