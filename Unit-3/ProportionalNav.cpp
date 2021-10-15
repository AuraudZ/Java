//Proportional Navigation
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <Windows.h>
struct Vector3
{
    float x;
    float y;
    float z;
};
int main () {
    APN_Update()
}
float APN_Update(Vector3 miss_pos,Vector3 taget_pos,float N,float Nt,){
    return (miss_pos-taget_pos)*N/Nt;
}
void Normalize()
{
	double mag = sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2));
	x = x / mag;
	y = y / mag;
	z = z / mag;
}
