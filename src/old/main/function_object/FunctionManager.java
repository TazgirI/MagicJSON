package net.tazgirl.magicjson.old.main.function_object;

import net.tazgirl.magicjson.old.Constants;
import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.old.PrivateCore;
import net.tazgirl.magicjson.old.main.addresses.FunctionAddress;
import net.tazgirl.magicjson.old.main.addresses.StatementAddress;
import net.tazgirl.magicjson.old.main.function_object.objects.BaseFunctionObject;
import net.tazgirl.magicjson.old.main.hook_object.HookParameters;
import net.tazgirl.magicjson.old.main.statement_object.StatementManager;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

@ParametersAreNonnullByDefault
public class FunctionManager
{
    ProcessFlag processFlag = ProcessFlag.RUN;

    Class<?> returnType = null;
    Object returnValue = Constants.trueEmpty;

    FunctionAddress functionAddress;

    Map<String, Object> args;

    int steps = 0;


    public FunctionManager(FunctionAddress functionAddress, Map<String, Object> args)
    {
        this.functionAddress = functionAddress;

        this.args = args;
    }



    public void Return(@Nullable Object newReturnValue, BaseFunctionObject origin)
    {
        processFlag = ProcessFlag.WAIT;

        // returnType is null (true), second half ignored TRUEx2
        // returnType is not null (false), newReturnValue matches returnType (true) TRUE
        // returnType is not null (false), newReturnValue does not match returnType (false) FALSE
        if(returnType == null || returnType.isInstance(newReturnValue))
        {
            this.returnValue = newReturnValue;

            processFlag = ProcessFlag.STOP;

            if(returnValue == null)
            {
                Logging.Info("Function has just set it's own return value to null, if this was on purpose then you can ignore this, however the most likely cause is a statement within it has broken and defaulted to null \n" + origin.LogLocation(), false);
            }

            return;
        }



        String errorMessage = "Invalid return type, process has been continued until it next attempts to terminate.\nAttempted return type: " + (newReturnValue == null ? "unknown" : newReturnValue.getClass()) + "\nExpected return type: " + returnType.toString() + "\n" + origin.LogLocation();
        Logging.Debug(errorMessage, false);

        processFlag = ProcessFlag.RUN;
    }

    public FunctionAddress getFunctionAddress()
    {
        return functionAddress;
    }

    public Map<String, Object> getArgs()
    {
        return args;
    }

    public Object checkArg(String id)
    {
        return args.get(id);
    }

    public void setReturnType(Class<?> newReturnType)
    {
        returnType = newReturnType;
    }

    public void addStep()
    {
        steps++;
    }

    public int getSteps()
    {
        return steps;
    }

    public ProcessFlag getProcessFlag()
    {
        return processFlag;
    }

    public Object getReturnValue()
    {
        return returnValue;
    }

    public Class<?> getReturnType()
    {
        return returnType;
    }

    public StatementManager generateStatementManager(String statementAddress)
    {
        return new StatementManager(StatementAddress.from(statementAddress), args);
    }

    public StatementManager generateStatementManager(StatementAddress statementAddress)
    {
        return new StatementManager(statementAddress, args);
    }

    public void runVoidHook(String hookName, HookParameters params)
    {
        PrivateCore.runVoidHook(hookName, params);
    }

    public FunctionManager copy()
    {
        return new FunctionManager(functionAddress, args);
    }

    public FunctionManager copyWithNewAddress(FunctionAddress newAddress)
    {
        return new FunctionManager(newAddress, args);
    }


    public enum ProcessFlag
    {
        RUN, // Continue as normal
        WAIT, // Check every millisecond if you are cleared to run
        STOP // Exit process
    }



}
