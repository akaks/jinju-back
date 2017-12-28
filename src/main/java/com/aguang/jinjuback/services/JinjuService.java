package com.aguang.jinjuback.services;

import com.aguang.jinjuback.configuration.CustomException;
import com.aguang.jinjuback.dao.JinjuDao;
import com.aguang.jinjuback.model.Jinju;
import com.aguang.jinjuback.pojo.JinjuInfo;
import com.aguang.jinjuback.pojo.Result;
import com.aguang.jinjuback.pojo.common.PageInfo;
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

    public Result createJinju(Jinju jinju) {
        jinjuDao.createJinju(jinju);
        Result result = new Result();
        result.setSuccess(null, "创建成功");
        return result;
    }

    public Jinju getJinju(int id) {
        Jinju jinju = jinjuDao.getJinju(id);
        return jinju;
    }

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
    public Result upVote(Integer jijuId, Integer userId) {
        Result result = new Result();
        try {
            jinjuDao.createVote(jijuId, userId, VoteConstant.UP, DateUtils.getCurrentTimeForInt());

            jinjuDao.increaseUpVote(jijuId);

            result.setMessage("点赞成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setCode(Result.NG);
            result.setMessage("点赞失败");
            LOG.error("点赞失败", e);
        }
        return result;
    }

    /**
     * 取消赞
     * @param jijuId
     * @param userId
     * @return
     */
    @Transactional
    public Result cancelUpVote(Integer jijuId, Integer userId) {
        Result result = new Result();
        try {
            Integer affectCount = jinjuDao.deleteVote(jijuId, userId);

            if(affectCount == 0) {
                throw new CustomException("当前没有点赞，不可取消!");
            }

            jinjuDao.decreaseUpVote(jijuId);

            result.setMessage("取消赞成功");
        } catch (Exception e) {
            // 手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setCode(Result.NG);
            result.setMessage("取消赞失败");
            LOG.error("取消赞失败", e);
        }
        return result;
    }

    /**
     * 点踩
     * @param jijuId
     * @param userId
     * @return
     */
    @Transactional
    public Result downVote(Integer jijuId, Integer userId) {
        Result result = new Result();
        try {
            jinjuDao.createVote(jijuId, userId, VoteConstant.DOWN, DateUtils.getCurrentTimeForInt());

            jinjuDao.increaseDownVote(jijuId);

            result.setMessage("点踩成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setCode(Result.NG);
            result.setMessage("点踩失败");
            LOG.error("点踩失败", e);
        }
        return result;
    }

    /**
     * 取消踩
     * @param jijuId
     * @param userId
     * @return
     */
    @Transactional
    public Result cancelDownVote(Integer jijuId, Integer userId) {
        Result result = new Result();
        try {
            Integer affectCount = jinjuDao.deleteVote(jijuId, userId);

            if(affectCount == 0) {
                throw new CustomException("当前没有点踩，不可取消!");
            }

            jinjuDao.decreaseDownVote(jijuId);

            result.setMessage("取消踩成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setCode(Result.NG);
            result.setMessage("取消踩失败");
            LOG.error("取消踩失败", e);
        }
        return result;
    }

    /**
     * 点赞变点踩
     * @param jijuId
     * @param userId
     * @return
     */
    @Transactional
    public Result upVoteToDown(Integer jijuId, Integer userId) {
        Result result = new Result();
        try {
            Integer affectCount = jinjuDao.updateVote(jijuId, userId, VoteConstant.DOWN, VoteConstant.UP, DateUtils.getCurrentTimeForInt());

            if(affectCount == 0) {
                throw new CustomException("当前没有点赞，不可变点踩!");
            }

            jinjuDao.decreaseUpVote(jijuId);

            jinjuDao.increaseDownVote(jijuId);

            result.setMessage("点赞变点踩成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setCode(Result.NG);
            result.setMessage("点赞变点踩失败");
            LOG.error("点赞变点踩失败", e);
        }
        return result;
    }

    /**
     * 点踩变点赞
     * @param jijuId
     * @param userId
     * @return
     */
    @Transactional
    public Result downVoteToUp(Integer jijuId, Integer userId) {
        Result result = new Result();
        try {
            Integer affectCount = jinjuDao.updateVote(jijuId, userId, VoteConstant.UP, VoteConstant.DOWN, DateUtils.getCurrentTimeForInt());

            if(affectCount == 0) {
                throw new CustomException("当前没有点踩，不可变点赞!");
            }

            jinjuDao.increaseUpVote(jijuId);

            jinjuDao.decreaseDownVote(jijuId);

            result.setMessage("点踩变点赞成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setCode(Result.NG);
            result.setMessage("点踩变点赞失败");
            LOG.error("点踩变点赞失败", e);
        }
        return result;
    }
}
