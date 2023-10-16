### Netty 编码/解码器

为 Channel Pipeline 添加 编码/解码器, 直接读取/写入Java对象
- 减少流程中对数据的处理
- 防止半包/粘包