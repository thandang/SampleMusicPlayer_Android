//
//  block_objects.c
//  SampleMusicPlayer
//
//  Created by Than Dang on 7/5/16.
//  Copyright © 2016 Than Dang. All rights reserved.
//

#include "block_objects.h"
#include "buffer.h"
#include "player.h"


//Generate VBO
PointData generatePointData(GLuint texture, Block blockData) {
    return (PointData) {texture, create_vbo(1, blockData.particles, GL_STREAM_DRAW)};
}

PointData generateBarPointData(GLuint texture, Bar barData) {
    return (PointData) {texture, create_vbo(NUM_POINTS, barData.particles, GL_STREAM_DRAW)};
}


void renderBlockCover(const PointData *data, const TextureProgram *texture_program, mat4x4 m, const InputData inputData) {
    
    glUseProgram(texture_program->program);
    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_2D, data->texture);
    
    glUniformMatrix4fv(texture_program->u_ProjectionMatrix, 1, GL_FALSE, (GLfloat*)m);
    glBindBuffer(GL_ARRAY_BUFFER, data->buffer);
    glUniform2f(texture_program->u_ePosition, (GLfloat)inputData.positionX, (GLfloat)inputData.positionY);
    
    glUniform1f(texture_program->u_eSizeStart, inputData.sizeStart);
    glUniform1f(texture_program->u_eSizeEnd, inputData.sizeEnd);
    glUniform1i(texture_program->u_Texture, 0);
    glUniform1f(texture_program->u_eDelta, inputData.delta);
    
    glDrawArrays(GL_POINTS, 0, 1);
    
    glBindBuffer(GL_ARRAY_BUFFER, 0); //Free up data
}

void renderBar(const PointData *data, const TextureProgram *textureProgram, mat4x4 m, const InputData inputData) {
    glUseProgram(textureProgram->program);
    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_2D, data->texture);
    
    glUniformMatrix4fv(textureProgram->u_ProjectionMatrix, 1, GL_FALSE, (GLfloat *)m);
    glBindBuffer(GL_ARRAY_BUFFER, data->buffer);
    glUniform2f(textureProgram->u_ePosition, (GLfloat)inputData.positionX, (GLfloat)inputData.nextPosition);
    glUniform1f(textureProgram->u_eSizeStart, inputData.sizeStart);
    glUniform1f(textureProgram->u_eSizeEnd, inputData.sizeEnd);
    glUniform1i(textureProgram->u_Texture, 0);
    glUniform1f(textureProgram->u_eDelta, inputData.delta2);
    
    glDrawArrays(GL_POINTS, 0, NUM_POINTS);
    
    glBindBuffer(GL_ARRAY_BUFFER, 0); //Free up data
}

void renderBottomBar(const PointData *data, const TextureProgram *textureProgram, mat4x4 m, const InputData inputData, float plus) {
    glUseProgram(textureProgram->program);
    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_2D, data->texture);
    
    glUniformMatrix4fv(textureProgram->u_ProjectionMatrix, 1, GL_FALSE, (GLfloat *)m);
    glBindBuffer(GL_ARRAY_BUFFER, data->buffer);
    glUniform2f(textureProgram->u_ePosition, (GLfloat)inputData.positionX + plus, BOTTOM_Y);
    glUniform1f(textureProgram->u_eSizeStart, HALF_POINT_SIZE);
    glUniform1f(textureProgram->u_eSizeEnd, HALF_POINT_SIZE);
    glUniform1i(textureProgram->u_Texture, 0);
    glUniform1f(textureProgram->u_eDelta, 0.0);
    
    glDrawArrays(GL_POINTS, 0, 1);
    
    glBindBuffer(GL_ARRAY_BUFFER, 0); //Free up data
}
