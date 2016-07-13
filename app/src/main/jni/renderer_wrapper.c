#include "Libraries/Core/player.h"
#include "Libraries/Core/macros.h"
#include "Libraries/Core/block_objects.h"
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

JNIEXPORT void JNICALL Java_com_elisoft_samplemusicplayer_RenderSurface_initial_1data(JNIEnv* env, jclass callingClass, jobjectArray inputObjects) {
	UNUSED(env);
	UNUSED(callingClass);
	int len = (*env)->GetArrayLength(env, inputObjects);
	InputData inputData[MAX_NUM_COLUMN] = {};
	int j;
	for (j = 0; j < len; ++j) { //len must be equal to MAX_NUM_COLUMN
		jobject obj = (jobject)(*env)->GetObjectArrayElement(env, inputObjects, j);
		InputData item;
		jclass cls = (*env)->GetObjectClass(env, obj);

		jfieldID fieldX = (*env)->GetFieldID(env, cls, "positionX", "F");
		jfloat positionX = (*env)->GetFloatField(env, obj, fieldX);
		printf("positionX: %f", positionX);
		item.positionX = positionX;

		jfieldID fieldY = (*env)->GetFieldID(env, cls, "positionY", "F");
		jfloat positionY = (*env)->GetFloatField(env, obj, fieldY);
		printf("positionY: %f", positionY);
		item.positionY = positionY;

		jfieldID sizeStartField = (*env)->GetFieldID(env, cls, "sizeStart", "F");
		jfloat sizeStart = (*env)->GetFloatField(env, obj, sizeStartField);
		item.sizeStart = sizeStart;

		jfieldID sizeEndField = (*env)->GetFieldID(env, cls, "sizeEnd", "F");
		jfloat sizeEnd = (*env)->GetFloatField(env, obj, sizeEndField);
		item.sizeEnd = sizeEnd;

		jfieldID deltaField = (*env)->GetFieldID(env, cls, "delta", "F");
		jfloat delta = (*env)->GetFloatField(env, obj, deltaField);
		item.delta = delta;

		jfieldID delta2Field = (*env)->GetFieldID(env, cls, "delta2", "F");
		jfloat delta2 = (*env)->GetFloatField(env, obj, delta2Field);
		item.delta2 = delta2;
		//A bit more properties but unnecessary
		inputData[j] = item;
	}

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