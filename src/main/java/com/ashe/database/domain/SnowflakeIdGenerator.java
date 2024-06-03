package com.ashe.database.domain;

public class SnowflakeIdGenerator {
    // 下面两个每个5位，加起来就是10位的工作机器id
    private long workerId;
    private long datacenterId;
    // 12位的序列号
    private long sequence = 0L;

    // 5位的机器id
    private long workerIdBits = 5L;
    // 5位的机房id
    private long datacenterIdBits = 5L;
    // 每毫秒内产生的id数 2 的 12 次方
    private long sequenceBits = 12L;

    // 机器ID向左移12位
    private long workerIdShift = sequenceBits;
    // 数据标识id向左移17位（12+5）
    private long datacenterIdShift = sequenceBits + workerIdBits;
    // 时间截向左移22位（5+5+12）
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    // 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    // 起始时间戳
    private long twepoch = 1288834974657L;

    private long lastTimestamp = -1L;

    public SnowflakeIdGenerator(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    // 产生下一个ID
    public synchronized long nextId() {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
}
