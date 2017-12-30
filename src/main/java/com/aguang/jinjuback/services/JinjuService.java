package com.aguang.jinjuback.services;

import com.aguang.jinjuback.configuration.CustomException;
import com.aguang.jinjuback.dao.JinjuDao;
import com.aguang.jinjuback.model.Jinju;
import com.aguang.jinjuback.pojo.JinjuInfo;
import com.aguang.jinjuback.pojo.Result;
import com.aguang.jinjuback.pojo.common.PageInfo;
import com.aguang.jinjuback.pojo.constants.IsDeleteConstant;
import com.aguang.jinjuback.pojo.constants.VoteConstant;
import com.aguang.jinjuback.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;

@Service
public class JinjuService {

    public final static Logger LOG = LoggerFactory.getLogger(JinjuService.class);

    @Autowired
    JinjuDao jinjuDao;

    /**
     * 创建金句
     * @param jinju
     * @param userId
     * @return
     */
    public Result createJinju(Jinju jinju, Integer userId) {
        Result result = new Result();
        try {
            Long currentTimeForInt = DateUtils.getCurrentTimeForInt();

            jinju.setUserId(userId);
            jinju.setUpVoteCount(0);
            jinju.setDownVoteCount(0);
            jinju.setCollectCount(0);
            jinju.setCommentCount(0);
            jinju.setCreateTime(currentTimeForInt);
            jinju.setUpdateTime(currentTimeForInt);
            jinju.setIsDelete(IsDeleteConstant.UN_DELETE);

            jinjuDao.createJinju(jinju);
            result.setSuccess(null, "创建成功");
        } catch (Exception e) {
            result.setError(null, "创建失败");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取金句信息
     * @param id
     * @return
     */
    public JinjuInfo getJinju(Integer id, Integer userId) {

        JinjuInfo jinju = null;
        // 当前有登录用户，需要取出该用户是否有点赞、收藏等信息
        if(userId != null) {
            jinju = jinjuDao.getJinjuWithUserId(id, userId);
        }
        // 当前没有登录用户，则直接取出金句列表
        else {
            jinju = jinjuDao.getJinjuWithoutUserId(id);
        }
        return jinju;
    }

    /**
     * 获取金桔句列表
     * @param pageIndex
     * @param pageSize
     * @param userId
     * @return
     */
    public Result getJinjuList(int pageIndex, int pageSize, Integer userId) {
        Integer m = (pageIndex - 1) * pageSize;
        ArrayList<JinjuInfo> list = null;

        // 当前有登录用户，需要取出该用户是否有点赞、收藏等信息
        if(userId != null) {
            list = jinjuDao.listByPageWithUserId(m, pageSize, userId);
        }
        // 当前没有登录用户，则直接取出金句列表
        else {
            list = jinjuDao.listByPageWithoutUserId(m, pageSize);
        }

        Integer total = jinjuDao.getListCount();

        PageInfo<JinjuInfo> pageInfo = new PageInfo(total, list);

        Result result = new Result();
        result.setSuccess(pageInfo, "金句列表数据成功");
        return result;
    }

    /**
     * 点赞
     * @param jijuId
     * @param userId
     * @return
     */
    @Transactional
    public Result upVote(Integer jijuId, String type, Integer userId) {
        Result result = new Result();
        try {
            // 1:点赞
            if("1".equals(type)) {

                Integer hasVote = jinjuDao.hasVote(jijuId, userId, VoteConstant.DOWN);
                // 判断当前是否存在踩，如果存在，则删除踩
                if(hasVote != null) {
                    jinjuDao.deleteVote(jijuId, userId, VoteConstant.DOWN);

                    jinjuDao.decreaseDownVote(jijuId);
                }

                jinjuDao.createVote(jijuId, userId, VoteConstant.UP, DateUtils.getCurrentTimeForInt());

                jinjuDao.increaseUpVote(jijuId);

            }
            // 1:取消赞
            else if("2".equals(type)) {
                Integer affectCount = jinjuDao.deleteVote(jijuId, userId, VoteConstant.UP);

                if(affectCount == 0) {
                    throw new CustomException("当前没有点赞，不可取消!");
                }

                jinjuDao.decreaseUpVote(jijuId);
            }

            result.setMessage("操作成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setError("操作失败");
            if(e instanceof CustomException) {
                result.setError(e.getMessage());
            }
            LOG.error("操作失败", e);
        }
        return result;
    }

    /**
     * 踩
     * @param jijuId
     * @param userId
     * @return
     */
    @Transactional
    public Result downVote(Integer jijuId, String type, Integer userId) {
        Result result = new Result();
        try {

            // 1:点踩
            if("1".equals(type)) {

                Integer hasVote = jinjuDao.hasVote(jijuId, userId, VoteConstant.UP);
                // 判断当前是否存在赞，如果存在，则删除赞
                if(hasVote != null) {
                    jinjuDao.deleteVote(jijuId, userId, VoteConstant.UP);

                    jinjuDao.decreaseUpVote(jijuId);
                }

                jinjuDao.createVote(jijuId, userId, VoteConstant.DOWN, DateUtils.getCurrentTimeForInt());

                jinjuDao.increaseDownVote(jijuId);

            }
            // 1:取消踩
            else if("2".equals(type)) {
                Integer affectCount = jinjuDao.deleteVote(jijuId, userId, VoteConstant.DOWN);

                if(affectCount == 0) {
                    throw new CustomException("当前没有点赞，不可取消!");
                }

                jinjuDao.decreaseDownVote(jijuId);
            }

            result.setMessage("操作成功");

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setError("操作失败");
            if(e instanceof CustomException) {
                result.setError(e.getMessage());
            }
            LOG.error("点踩失败", e);
        }
        return result;
    }

    /**
     * 收藏
     * @param jijuId
     * @param userId
     * @return
     */
    @Transactional
    public Result collect(Integer jijuId, Integer userId) {
        Result result = new Result();
        try {
            jinjuDao.createCollect(jijuId, userId, 1,DateUtils.getCurrentTimeForInt());

            jinjuDao.increaseCollect(jijuId);

            result.setMessage("收藏成功!");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setCode(Result.NG);
            result.setMessage("收藏失败!");
            LOG.error("收藏成功!", e);
        }
        return result;
    }

    /**
     * 取消收藏
     * @param jijuId
     * @param userId
     * @return
     */
    @Transactional
    public Result cancelCollect(Integer jijuId, Integer userId) {
        Result result = new Result();
        try {
            Integer affectCount = jinjuDao.deleteCollect(jijuId, userId);

            if(affectCount == 0) {
                throw new CustomException("当前没有收藏，不可取消!");
            }

            jinjuDao.decreaseCollect(jijuId);

            result.setMessage("取消收藏成功!");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setCode(Result.NG);
            result.setMessage("取消收藏失败!");
            LOG.error("取消收藏失败!", e);
        }
        return result;
    }
}
