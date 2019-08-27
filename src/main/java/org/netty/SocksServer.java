package org.netty;

import org.netty.proxy.HostHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class SocksServer {

	private static Logger logger = LoggerFactory.getLogger(SocksServer.class);

	private EventLoopGroup bossGroup = null;
	private EventLoopGroup workerGroup = null;
	private ServerBootstrap bootstrap = null;

	public static final String METHOD = "aes-256-ofb";

	public void start(int port, String password) {
		try {
			bossGroup = new NioEventLoopGroup(1);
			workerGroup = new NioEventLoopGroup(2);
			bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_KEEPALIVE, true).childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addLast(new HostHandler(METHOD,password));
						}
					});
			logger.info("Start a SS server at:[{}], method:[{}], password:[{}]",port,METHOD,password);
			bootstrap.bind(port).sync().channel().closeFuture().sync();
		} catch (Exception e) {
			logger.error("start error", e);
		} finally {
			stop();
		}
	}

	public void stop() {
		if (bossGroup != null) {
			bossGroup.shutdownGracefully();
		}
		if (workerGroup != null) {
			workerGroup.shutdownGracefully();
		}
		logger.info("Stop Server!");
	}

}
