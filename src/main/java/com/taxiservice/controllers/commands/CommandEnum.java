package com.taxiservice.controllers.commands;

import com.taxiservice.controllers.commands.actions.*;
import com.taxiservice.controllers.exceptions.WrongCommandException;
import com.taxiservice.models.services.*;

public enum CommandEnum {

    PROFILE ("PROFILE", new ProfileCommand()) {},
    EMPTY ("EMPTY", new EmptyCommand()) {},
    LOGIN ("LOGIN", new LoginCommand(new UserServiceImpl())) {},
    REGISTRATION("REGISTRATION", new RegistrationCommand(new UserServiceImpl())){},
    LOGOUT ("LOGOUT", new LogoutCommand()) {},
    USER ("USER", new UserCommand(new UserServiceImpl(), new OrderServiceImpl())) {},
    ADMIN ("ADMIN", new AdminCommand(new TariffServiceImpl())) {},
    ROUTES ("ROUTES", new RoutesCommand(new RouteServiceImpl())) {},
    TARIFFS ("TARIFFS", new TariffsCommand(new TariffServiceImpl())) {},
    CALCULATOR ("CALCULATOR", new CalculatorCommand(new RouteServiceImpl(), new TariffServiceImpl())) {},
    CALCULATE ("CALCULATE", new CalculateDeliveryPriceAjaxCommand(new RouteServiceImpl(), new TariffServiceImpl(), new CalculatorServiceImpl())) {},
    TARIFFSBYROUTE("GETTARIFFLISTBYROUTE", new GetTariffListByRouteAjaxCommand(new TariffServiceImpl())) {},
    ORDERCREATOR("USER/ORDERCREATOR", new OrderCreatorCommand(new RouteServiceImpl(), new TariffServiceImpl())),
    CREATEORDER("USER/CREATEORDER", new CreateOrderAjaxCommand(new CalculatorServiceImpl(), new OrderServiceImpl(), new AvailableOptionServiceImpl())),
    ACCOUNTINVOICE("USER/ACCOUNT/INVOICE", new AccountInvoiceCommand()),
    ACCOUNTREFILL("USER/ACCOUNT/REFILL", new AccountRefillAjaxCommand(new UserServiceImpl())),
    BILLPAY("USER/BILL/PAY", new BillPayAjaxCommand(new BillServiceImpl())),
    ROUTESMANAGER ("ADMIN/ROUTES/MANAGER", new RoutesManagerCommand(new RouteServiceImpl(), new TariffServiceImpl())) {},
    TARIFFEDIT ("ADMIN/TARIFF/EDIT", new TariffEditAjaxCommand(new TariffServiceImpl())) {},
    OPTIONEDIT("ADMIN/OPTION/EDIT", new OptionEditAjaxCommand(new AvailableOptionServiceImpl()));

    private String commandPath;
    private Command command;

    CommandEnum(String commandPath, Command command){
        this.commandPath = commandPath;
        this.command = command;
    }

    public Command getCurrentCommand() {
        return command;
    }

    public static Command getByPath(String path) throws WrongCommandException {
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if (commandEnum.commandPath.equalsIgnoreCase(path))
                return commandEnum.command;
        }
        throw new WrongCommandException(path);
    }

}