#include "Libraries/Core/player.h"
#include "Libraries/Core/macros.h"
#include <jni.h>

/* These functions are called from Java. */

JNIEXPORT void JNICALL Java_com_elisoft_samplemusicplayer_RenderSurface_setup_1screen(JNIEnv * env, jclass cls) {
	UNUSED(env);
	UNUSED(cls);
	setup_screen();
}

JNIEXPORT void JNICALL Java_com_elisoft_samplemusicplayer_RenderSurface_on_1surface_1changed(JNIEnv * env, jclass cls, jint width, jint height) {
	UNUSED(env);
	UNUSED(cls);
	on_surface_changed(width, height);
}

JNIEXPORT void JNICALL Java_com_elisoft_samplemusicplayer_RenderSurface_initial_1data(JNIEnv* env, jclass cls, InputData inputData[]) {
	UNUSED(env);
	UNUSED(cls);
	initial_data(inputData);
}

JNIEXPORT void JNICALL Java_com_elisoft_samplemusicplayer_RenderSurface_render_1blocks(JNIEnv* env, jclass cls) {
	UNUSED(env);
	UNUSED(cls);
	render_blocks();
}

JNIEXPORT void JNICALL Java_com_elisoft_samplemusicplayer_RenderSurface_update_1blocks(JNIEnv* env, jclass cls) {
	UNUSED(env);
	UNUSED(cls);
	update_blocks();
}

JNIEXPORT void JNICALL Java_com_elisoft_samplemusicplayer_RenderSurface_update_1block_1at_1index(JNIEnv* env, jclass cls,
																							 jint index) {
	UNUSED(env);
	UNUSED(cls);
	update_block_at_index(index);
}
/*
JNIEXPORT jobject JNICALL Java_com_elisoft_samplemusicplayer_RenderSurface_get_1input_1data(JNIEnv *env, jclass cls, jobject info) {
	jclass myClass = (*env)->GetObjectClass(env, info);
	jmethodID constructor = (*env)->GetMethodID(env, myClass, "<init>", "()/models/InputData");
	jobject instance = (*env)->NewObject(env, myClass, constructor);
	return instance;

}
*/