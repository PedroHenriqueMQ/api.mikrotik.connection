package edu.catolica.api.mikrotik.connection.domain.constants;

public class MikrotikCommands {
    public static final String CREATE_USER = "/ip/hotspot/user/add name=%s password=\"%s\" comment=\"%s\" profile=default limit-uptime=1h";
    public static final String DELETE_USER = "/ip/hotspot/user/remove .id=%s";
    public static final String UPDATE_USER = "/ip/hotspot/user/set .id=%s name=%s password=\"%s\" comment=\"%s\"";
    public static final String SEARCH_USER = "/ip/hotspot/user/print where name=%s";
}
