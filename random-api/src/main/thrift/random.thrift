#@namespace scala demo.thrift.random

exception TMsError {
  1: required i32 code;
  2: optional string message;
}

struct TNumberRequest {
  1:  required i32          min;
  2:  required i32          max;
  3:  required i32          elements;
}

struct TNumberResponse {
  1:  required i32          num;
  2:  required i32          count;
  3:  required i32          elements;
}

service TRandomNumber  {
  list<TNumberResponse> generateNumber(0: TNumberRequest numberRequest) throws (1:TMsError error)
}
