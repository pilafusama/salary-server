package com.tenpay.wxwork.salary.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import com.tenpay.bap.monitor.udp.AccLogBean;
import com.tenpay.bap.monitor.udp.MonitorUdpLogSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 上报监控和 acc 日志服务
 *
 */
@Service
public class MonitorLogService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorLogService.class);

    //    @Value("${monitor.caller.networkInterface}")
    private String networkInterface;

    private String localIp;

    @Value("${monitor.caller.serverName}")
    private String serverName;
    
    @Autowired
    private MonitorUdpLogSender monitorUdpLogSender;

    public void sendAccLog(String srcIp, String localIp, String service, String resultCode, long costTimeUs) {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String now = simpleDateFormat.format(date);
        
        AccLogBean log = new AccLogBean();
        log.setTimestamp(now);
        log.setSrcIp(srcIp);
        log.setDestIp(localIp);
        log.setDestServer(serverName);
        log.setDestService(service);
        log.setResCode(resultCode);
        log.setCostTimeUs(String.valueOf(costTimeUs));

        monitorUdpLogSender.sendAccLog(log);
    }

    private String getLocalIp() {
        if (localIp.isEmpty()) {
            localIp = getIpByInterface(networkInterface);
        }

        return localIp;
    }

    private String getIpByInterface(String networkInterfaceName) {
        String ip = "1.2.3.4";
        try {
            NetworkInterface networkInterface = NetworkInterface.getByName(networkInterfaceName);    
        
            Enumeration<InetAddress> inetAddress = networkInterface.getInetAddresses();
            InetAddress currentAddress;
            currentAddress = inetAddress.nextElement();

            while(inetAddress.hasMoreElements())
            {
                currentAddress = inetAddress.nextElement();
                if(currentAddress instanceof Inet4Address && !currentAddress.isLoopbackAddress())
                {
                    ip = currentAddress.toString();
                    break;
                }
            }
        }
        catch (SocketException e) {
            logger.error("failed to get ip");
        }
        return ip;
    }
}
