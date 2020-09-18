namespace java me.sample.rpc.thrift.api

service HelloThriftService {
    string say(1: string word)
}
